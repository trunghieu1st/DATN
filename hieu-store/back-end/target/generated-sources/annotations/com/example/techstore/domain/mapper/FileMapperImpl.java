package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.FileDto;
import com.example.techstore.domain.entity.File;
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
public class FileMapperImpl implements FileMapper {

    @Override
    public FileDto mapFileToFileDto(File file) {
        if ( file == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        if ( file.getLastModifiedDate() != null ) {
            fileDto.setLastModifiedDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ).format( file.getLastModifiedDate() ) );
        }
        if ( file.getCreatedDate() != null ) {
            fileDto.setCreatedDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ).format( file.getCreatedDate() ) );
        }
        fileDto.setId( file.getId() );
        fileDto.setPath( file.getPath() );
        fileDto.setSize( file.getSize() );
        fileDto.setName( file.getName() );
        fileDto.setCreatedBy( file.getCreatedBy() );
        fileDto.setLastModifiedBy( file.getLastModifiedBy() );

        return fileDto;
    }

    @Override
    public List<FileDto> mapFileToFileDto(List<File> files) {
        if ( files == null ) {
            return null;
        }

        List<FileDto> list = new ArrayList<FileDto>( files.size() );
        for ( File file : files ) {
            list.add( mapFileToFileDto( file ) );
        }

        return list;
    }
}
