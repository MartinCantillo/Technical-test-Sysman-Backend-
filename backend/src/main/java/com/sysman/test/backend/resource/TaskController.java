package com.sysman.test.backend.resource;

import com.sysman.test.backend.model.Task;
import com.sysman.test.backend.service.TaskService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {


    private final TaskService taskService = new TaskService();



    @GET
    public Response getAllTasks() {

        List<Task> tasks = taskService.getAllTasks();

        return Response
                .ok(tasks)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") Long id) {

        Task task = taskService.getTaskById(id);

        if (task == null) {

            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Task not found")
                    .build();
        }

        return Response
                .ok(task)
                .build();
    }

    @POST
    public Response createTask(Task task) {

        boolean created = taskService.createTask(task);

        if (!created) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating task")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Task created successfully")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTask(
            @PathParam("id") Long id,
            Task task
    ) {

        task.setTaskId(id);

        boolean updated = taskService.updateTask(task);

        if (!updated) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating task")
                    .build();
        }

        return Response
                .ok("Task updated successfully")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Long id) {

        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting task")
                    .build();
        }

        return Response
                .ok("Task deleted successfully")
                .build();
    }
}
