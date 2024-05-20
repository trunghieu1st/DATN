package com.example.techstore.domain.mapper;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.domain.dto.request.MessageRequestDto;
import com.example.techstore.domain.dto.response.MessageDto;
import com.example.techstore.domain.entity.Message;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper {
    Message mapMessageCreateDtoToMessage(MessageRequestDto createDto);

    @Mappings({
            @Mapping(target = "roomId", source = "room.id"),
            @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", dateFormat = CommonConstant.PATTERN_DATE_TIME),
            @Mapping(target = "createdDate", source = "createdDate", dateFormat = CommonConstant.PATTERN_DATE_TIME),
            @Mapping(target = "fileDtos", source = "files"),

    })
    MessageDto mapMessageToMessageDto(Message message);

    List<MessageDto> mapMessageToMessageDto(List<Message> messages);

    void updateMessage(@MappingTarget Message message, MessageRequestDto updateDto);
}