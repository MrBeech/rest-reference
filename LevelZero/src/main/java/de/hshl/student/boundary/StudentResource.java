package de.hshl.student.boundary;

import de.hshl.student.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    List<Student> students;
    Student student;

    @PostConstruct
    void init()
    {
        student = new Student();
        students = new ArrayList<Student>();
        student.setName("Musterstudent");
        student.setId(1111);
        student.setCourse("ISD");
        students.add(student);
    }

    @Path("")
    @POST
    public Response students(Student student)
    {
        if(student.getOp().equals("create")) {
            students.add(student);
            return Response.status(Response.Status.CREATED).entity(student).build();
        }
        else if(student.getOp().equals("read")) {
            List<Student> result = findStudent(student.getId());
            return Response.status(Response.Status.OK).entity(result.get(0)).build();
        }
        else if(student.getOp().equals("update")) {
            List<Student> result = findStudent(student.getId());
            result.get(0).setName(student.getName());
            result.get(0).setCourse(student.getCourse());
            result.get(0).setOp(student.getOp());
            return Response.status(Response.Status.ACCEPTED).entity(result.get(0)).build();
        }
        else if(student.getOp().equals("delete")) {
            List<Student> result = findStudent(student.getId());
            students.removeAll(result);
            return Response.status(Response.Status.ACCEPTED).entity(students).build();
            }
        else if(student.getOp().equals("getStudents"))
            return Response.status(Response.Status.OK).entity(students).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity(student).build();
    }

    private List<Student> findStudent(int id){
        return students.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
    }
}
