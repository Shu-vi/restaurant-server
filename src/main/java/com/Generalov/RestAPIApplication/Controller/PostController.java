package com.Generalov.RestAPIApplication.Controller;


import com.Generalov.RestAPIApplication.Entity.PostEntity;
import com.Generalov.RestAPIApplication.Repository.PostRepository;
import com.Generalov.RestAPIApplication.Resource.PostResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    PostResource[] getAll(@RequestParam(required = false) String name) {
        PostEntity[] entities = postRepository.select();
        return Arrays.stream(entities)
                .map(entity -> {
                    PostResource resource = new PostResource(entity);
                    return resource;
                })
                .toArray(PostResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    PostResource get(@PathVariable Integer id,
                     @RequestParam(required = false) Object expand) {
        PostEntity entity = postRepository.select(id);
        if (entity == null) return null;
        PostResource resource = new PostResource(entity);
//        if (expand != null)
//            resource.setName(
//                    new EmployeeResource(employeeRepository.select(entity.getId()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    PostResource post(@RequestBody PostResource resource) {
        PostEntity entity = postRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new PostResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    PostResource put(@PathVariable Integer id,
                     @RequestBody PostResource resource) {
        PostEntity entity = postRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new PostResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    PostResource delete(@PathVariable Integer id) {
        PostEntity entity = postRepository.delete(id);
        if (entity == null) return null;
        return new PostResource(entity);
    }
}
