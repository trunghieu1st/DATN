package com.example.techstore.service.impl;

import com.example.techstore.constant.*;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.ChangePasswordRequestDto;
import com.example.techstore.domain.dto.request.NewPasswordRequestDto;
import com.example.techstore.domain.dto.request.UserCreateDto;
import com.example.techstore.domain.dto.request.UserUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.UserDto;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.mapper.UserMapper;
import com.example.techstore.exception.AlreadyExistException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.RoleRepository;
import com.example.techstore.repository.UserRepository;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.RoomService;
import com.example.techstore.service.UserService;
import com.example.techstore.util.PaginationUtil;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UploadFileUtil uploadFileUtil;
    private final RoomService roomService;

    public User getById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    }

    @Override
    public UserDto getUserDtoById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public PaginationResponseDto<UserDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
//        paginationFullRequestDto.setIsAscending(true);
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_USER_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.USER);

        //Create Output
        Page<User> userPage = userRepository.findAll(pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.USER, userPage);

        List<UserDto> userDtos =
                userMapper.mapUsersToUserDtos(userPage.getContent());
        return new PaginationResponseDto<>(meta, userDtos);
    }

    @Override
    public UserDto getCurrentUser(UserPrincipal user) {
        return this.getUserDtoById(user.getId());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        if (userRepository.existsByPhone(userCreateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userCreateDto.getPhone()});
        }
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_EMAIL, new String[]{userCreateDto.getEmail()});
        }
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userCreateDto.getUsername()});
        }
        User user = userMapper.mapUserCreateDtoToUser(userCreateDto);
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRole(roleRepository.findByName(RoleConstant.USER));
        user.setEnabled(true);
        userRepository.save(user);
//        roomService.create(user.getId());
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(String id, UserUpdateDto userUpdateDto) {
        User user = this.getById(id);
        if (!user.getPhone().equals(userUpdateDto.getPhone()) && userRepository.existsByPhone(userUpdateDto.getPhone())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_PHONE, new String[]{userUpdateDto.getPhone()});
        }
        if (!user.getEmail().equals(userUpdateDto.getEmail()) && userRepository.existsByEmail(userUpdateDto.getEmail())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_EMAIL, new String[]{userUpdateDto.getEmail()});
        }
        if (!user.getUsername().equals(userUpdateDto.getUsername()) && userRepository.existsByUsername(userUpdateDto.getUsername())) {
            throw new AlreadyExistException(ErrorMessage.User.ERR_ALREADY_EXIST_USERNAME, new String[]{userUpdateDto.getUsername()});
        }

        userMapper.updateUser(user, userUpdateDto);
        if (userUpdateDto.getAvatar() != null) {
            if (user.getAvatar() != null) {
                uploadFileUtil.destroyImageWithUrl(user.getAvatar());
            }
            user.setAvatar(uploadFileUtil.uploadImage(userUpdateDto.getAvatar()));
        }
        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    @Override
    public CommonResponseDto changePassword(String id, ChangePasswordRequestDto changePasswordRequestDto) {
        User user = this.getById(id);
        if (!passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), user.getPassword())) {
            return new CommonResponseDto(false, MessageConstant.CURRENT_PASSWORD_INCORRECT);
        }

        if (changePasswordRequestDto.getCurrentPassword().equals(changePasswordRequestDto.getNewPassword())) {
            return new CommonResponseDto(false, MessageConstant.SAME_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
        userRepository.save(user);
        return new CommonResponseDto(true, MessageConstant.CHANGE_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public CommonResponseDto createNewPassword(String id, NewPasswordRequestDto newPasswordRequestDto) {
        User user = this.getById(id);
        user.setPassword(passwordEncoder.encode(newPasswordRequestDto.getPassword()));
        userRepository.save(user);
        return new CommonResponseDto(true, MessageConstant.CREATE_NEW_PASSWORD_SUCCESSFULLY);
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        User user = this.getById(id);
        if (user.getAvatar() != null) {
            uploadFileUtil.destroyImageWithUrl(user.getAvatar());
        }
        userRepository.delete(user);
        return new CommonResponseDto(true, MessageConstant.DELETE_USER_SUCCESSFULLY);
    }

}
