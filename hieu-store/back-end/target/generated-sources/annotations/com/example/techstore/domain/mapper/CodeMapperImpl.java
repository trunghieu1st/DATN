package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.CodeDto;
import com.example.techstore.domain.entity.Code;
import com.example.techstore.domain.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CodeMapperImpl implements CodeMapper {

    @Override
    public CodeDto codeToCodeResponseDto(Code code) {
        if ( code == null ) {
            return null;
        }

        CodeDto codeDto = new CodeDto();

        codeDto.setExpirationTime( code.getExpirationTime() );
        codeDto.setEmail( codeUserEmail( code ) );
        codeDto.setUserId( codeUserId( code ) );
        codeDto.setCreatedDate( code.getCreatedDate() );
        codeDto.setLastModifiedDate( code.getLastModifiedDate() );
        if ( code.getId() != null ) {
            codeDto.setId( String.valueOf( code.getId() ) );
        }
        codeDto.setVerificationCode( code.getVerificationCode() );
        codeDto.setValid( code.getValid() );

        return codeDto;
    }

    private String codeUserEmail(Code code) {
        if ( code == null ) {
            return null;
        }
        User user = code.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String codeUserId(Code code) {
        if ( code == null ) {
            return null;
        }
        User user = code.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
