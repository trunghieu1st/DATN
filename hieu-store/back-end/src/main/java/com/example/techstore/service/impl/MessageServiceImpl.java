package com.example.techstore.service.impl;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.techstore.constant.CommonConstant;
import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.MessageRequestDto;
import com.example.techstore.domain.dto.response.MessageDto;
import com.example.techstore.domain.entity.*;
import com.example.techstore.domain.mapper.MessageMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.*;
import com.example.techstore.service.MessageService;
import com.example.techstore.util.PaginationUtil;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRoomRepository userRoomRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;
    private final FileRepository fileRepository;
    private final RoomRepository roomRepository;
    private final UploadFileUtil uploadFileUtil;
    private final SocketIOServer server;

    @Override
    public PaginationResponseDto<MessageDto> getAll(String roomId, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.MESSAGE);
        Page<Message> messagePage = messageRepository.getAll(roomId, pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.MESSAGE, messagePage);

        List<MessageDto> messageDtos = messageMapper.mapMessageToMessageDto(messagePage.getContent());
        return new PaginationResponseDto<>(meta, messageDtos);
    }

    @Override
    public MessageDto create(String userId, MessageRequestDto messageRequestDto) {

        Room room = roomRepository.findById(messageRequestDto.getRoomId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Room.ERR_NOT_FOUND_ID, new String[]{messageRequestDto.getRoomId()}));
        if (!userRoomRepository.existsUserRoomById(new UserRoomId(room.getId(), userId))) {
            throw new NotFoundException(ErrorMessage.UserRoom.ERR_USER_NOT_IN_ROOM, new String[]{userId,messageRequestDto.getRoomId()});
        }

        List<MultipartFile> multipartFiles = messageRequestDto.getMultipartFile();
        String messageContent = messageRequestDto.getMessage();
        if ((messageContent == null || messageContent.isEmpty()) && (multipartFiles == null || multipartFiles.isEmpty())) {
            throw new NotFoundException(ErrorMessage.Message.ERR_NOT_FOUND_MESSAGE_OR_FILE);
        }
        if (messageContent != null && messageContent.isEmpty()) {
            messageRequestDto.setMessage(null);
        }
        Message me = new Message();
        me.setMessage(messageRequestDto.getMessage());
        me.setRoom(room);
        messageRepository.save(me);

        List<File> files = new ArrayList<>();
        if (multipartFiles != null) {
            for (MultipartFile multipartFile : multipartFiles) {
                File file = new File();
                file.setPath(uploadFileUtil.uploadImage(multipartFile));
                file.setName(multipartFile.getOriginalFilename());
                file.setSize(multipartFile.getSize());
                file.setMessage(me);
                fileRepository.save(file);
                files.add(file);
            }
            me.setFiles(files);
        }

        MessageDto messageDto = messageMapper.mapMessageToMessageDto(messageRepository.save(me));

        List<UserRoom> userRooms = userRoomRepository.getAllUserByRoomId(room.getId());
        for (UserRoom userRoom : userRooms) {
            server.getRoomOperations(userRoom.getUser().getId())
                    .sendEvent(CommonConstant.Event.SERVER_SEND_MESSAGE, messageDto);
        }
        return messageDto;

    }

}