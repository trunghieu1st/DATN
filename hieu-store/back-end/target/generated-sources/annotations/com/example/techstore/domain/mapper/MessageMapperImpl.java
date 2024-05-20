package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.MessageRequestDto;
import com.example.techstore.domain.dto.response.FileDto;
import com.example.techstore.domain.dto.response.MessageDto;
import com.example.techstore.domain.entity.File;
import com.example.techstore.domain.entity.Message;
import com.example.techstore.domain.entity.Message.MessageBuilder;
import com.example.techstore.domain.entity.Room;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message mapMessageCreateDtoToMessage(MessageRequestDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        MessageBuilder message = Message.builder();

        message.message( createDto.getMessage() );

        return message.build();
    }

    @Override
    public MessageDto mapMessageToMessageDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setRoomId( messageRoomId( message ) );
        if ( message.getLastModifiedDate() != null ) {
            messageDto.setLastModifiedDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ).format( message.getLastModifiedDate() ) );
        }
        if ( message.getCreatedDate() != null ) {
            messageDto.setCreatedDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ).format( message.getCreatedDate() ) );
        }
        messageDto.setFileDtos( fileListToFileDtoList( message.getFiles() ) );
        messageDto.setId( message.getId() );
        messageDto.setMessage( message.getMessage() );
        messageDto.setCreatedBy( message.getCreatedBy() );
        messageDto.setLastModifiedBy( message.getLastModifiedBy() );

        return messageDto;
    }

    @Override
    public List<MessageDto> mapMessageToMessageDto(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( mapMessageToMessageDto( message ) );
        }

        return list;
    }

    @Override
    public void updateMessage(Message message, MessageRequestDto updateDto) {
        if ( updateDto == null ) {
            return;
        }

        if ( updateDto.getMessage() != null ) {
            message.setMessage( updateDto.getMessage() );
        }
    }

    private String messageRoomId(Message message) {
        if ( message == null ) {
            return null;
        }
        Room room = message.getRoom();
        if ( room == null ) {
            return null;
        }
        String id = room.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected FileDto fileToFileDto(File file) {
        if ( file == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        fileDto.setId( file.getId() );
        fileDto.setPath( file.getPath() );
        fileDto.setSize( file.getSize() );
        fileDto.setName( file.getName() );
        if ( file.getCreatedDate() != null ) {
            fileDto.setCreatedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( file.getCreatedDate() ) );
        }
        if ( file.getLastModifiedDate() != null ) {
            fileDto.setLastModifiedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( file.getLastModifiedDate() ) );
        }
        fileDto.setCreatedBy( file.getCreatedBy() );
        fileDto.setLastModifiedBy( file.getLastModifiedBy() );

        return fileDto;
    }

    protected List<FileDto> fileListToFileDtoList(List<File> list) {
        if ( list == null ) {
            return null;
        }

        List<FileDto> list1 = new ArrayList<FileDto>( list.size() );
        for ( File file : list ) {
            list1.add( fileToFileDto( file ) );
        }

        return list1;
    }
}
