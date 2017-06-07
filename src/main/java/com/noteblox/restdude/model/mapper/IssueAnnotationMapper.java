/**
 * This file is part of NoteBLOX.
 *
 * NoteBLOX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoteBLOX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with NoteBLOX.  If not, see <https://www.gnu.org/licenses/agpl-3.0.en.html>.
 */
package com.noteblox.restdude.model.mapper;

import com.noteblox.restdude.model.Issue;
import com.noteblox.restdude.model.dto.Annotation;
import com.restdude.domain.users.model.User;
import com.restdude.domain.users.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueAnnotationMapper {

    IssueAnnotationMapper INSTANCE = Mappers.getMapper( IssueAnnotationMapper.class );

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "createdDate", target = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"),
            @Mapping(source = "lastModifiedDate", target = "updated", dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"),
            @Mapping(source = "detail", target = "text"),
            @Mapping(source = "createdBy", target = "user")

    })
    Annotation toAnnotation(Issue note);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "created", target = "createdDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"),
            @Mapping(source = "updated", target = "lastModifiedDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"),
            @Mapping(source = "text", target = "detail"),
            @Mapping(source = "user", target = "createdBy")

    })
    Issue toAnnotation(Annotation ann);

    default UserDTO userToCreatedBy(User user){
        return UserDTO.fromUser(user);
    }
    default User createdByToUser(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        return user;
    }
}