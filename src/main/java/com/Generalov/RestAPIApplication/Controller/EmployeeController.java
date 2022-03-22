package com.Generalov.RestAPIApplication.Controller;


import com.Generalov.RestAPIApplication.Entity.EmployeeEntity;
import com.Generalov.RestAPIApplication.Repository.EmployeeRepository;
import com.Generalov.RestAPIApplication.Repository.PostRepository;
import com.Generalov.RestAPIApplication.Resource.EmployeeResource;
import com.Generalov.RestAPIApplication.Resource.PostResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final PostRepository postRepository;

    public EmployeeController(EmployeeRepository employeeRepository, PostRepository postRepository) {
        this.employeeRepository = employeeRepository;
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    EmployeeResource[] getAll(@RequestParam(required = false) Integer postId,
                              @RequestParam(required = false) Object expand) {
        EmployeeEntity[] entities = postId == null ?
                employeeRepository.select() :
                employeeRepository.selectByPostId(postId);
        return Arrays.stream(entities)
                .map(entity -> {
                    EmployeeResource resource = new EmployeeResource(entity);
                    if (expand!=null)
                        resource.setPostResource(new PostResource(
                                postRepository.select(entity.getPost()))
                        );
                    return resource;
                })
                .toArray(EmployeeResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    EmployeeResource get(@PathVariable Integer id,
                         @RequestParam(required = false) Object expand) {
        EmployeeEntity entity = employeeRepository.select(id);
        if (entity == null) return null;
        EmployeeResource resource = new EmployeeResource(entity);
        if (expand != null)
            resource.setPostResource(
                    new PostResource(postRepository.select(entity.getPost()))
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    EmployeeResource post(@RequestBody EmployeeResource resource) {
        EmployeeEntity entity = employeeRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new EmployeeResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    EmployeeResource put(@PathVariable Integer id,
                        @RequestBody EmployeeResource resource) {
        EmployeeEntity entity = employeeRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new EmployeeResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    EmployeeResource delete(@PathVariable Integer id) {
        EmployeeEntity entity = employeeRepository.delete(id);
        if (entity == null) return null;
        return new EmployeeResource(entity);
    }
}
