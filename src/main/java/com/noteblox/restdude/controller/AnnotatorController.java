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
package com.noteblox.restdude.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.noteblox.restdude.model.Note;
import com.noteblox.restdude.model.dto.Annotation;
import com.noteblox.restdude.model.dto.Annotations;
import com.noteblox.restdude.model.mapper.NoteAnnotationMapper;
import com.noteblox.restdude.service.NoteService;
import com.restdude.domain.Model;
import com.restdude.mdd.annotation.model.ModelDrivenPreAuth;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rest/annotator")
public class AnnotatorController {

    NoteService noteService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get annotations for URL", notes = "Get all notes annotating the given URL as Annotation DTO instances.")
    public Annotations getAnnotationsFor(@RequestParam(value = "uri", required = false) String uri, HttpServletRequest request, HttpServletResponse response) {
        //http://localhost:8080/noteblox/api/rest/notes/search?uri=http%3A%2F%2F127.0.0.1%3A4000%2FNoteblox-Server%2Findex.html
        List<Annotation> annotations = new LinkedList<>();
        log.debug("getAnnotationsFor uri: {}", uri);
        annotations = this.noteService.findAnnotationsForUrl(uri);
        return new Annotations(annotations);
    }

    @RequestMapping(
            method = {RequestMethod.POST}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new resource")
    @JsonView({Model.ItemView.class})
    @ModelDrivenPreAuth
    public Annotation create(@RequestBody Annotation model) {
        Note note = NoteAnnotationMapper.INSTANCE.noteToAnnotation(model);
        note = this.noteService.create(note);
        return NoteAnnotationMapper.INSTANCE.noteToAnnotation(note);
    }


    @RequestMapping(method = RequestMethod.OPTIONS)
    @ApiOperation(value = "Get annotations for URL", notes = "Get all notes annotating the given URL as Annotation DTO instances.")
    public void getAnnotationsForPreFlight(@RequestParam(value = "uri", required = false) String uri, HttpServletRequest request, HttpServletResponse response) {
        //http://example.com/api/search?uri=http%3A%2F%2F127.0.0.1%3A4000%2FNoteblox-Server%2F
        log.debug("getAnnotationsForPreFlight uri: {}", uri);
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            log.debug("getAnnotationsForPreFlight header {}: {} ", headerName, request.getHeader(headerName));

        }
        //this.addCorsHeaders(response, "*");
    }

    protected void addCorsHeaders(HttpServletResponse response, String origin){
        response.addHeader("Access-Control-Allow-Origin", origin);
        if(response.getHeader("Access-Control-Allow-Headers")==null) {
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-Range, Range, Content-Type, Accept");
        }else{
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, X-Range, Range, Content-Type, Accept," + response.getHeader("Access-Control-Allow-Headers"));
        }
        response.addHeader("Access-Control-Expose-Headers","Accept-Ranges, Content-Encoding, Content-Length, Content-Range");

    }

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
}
