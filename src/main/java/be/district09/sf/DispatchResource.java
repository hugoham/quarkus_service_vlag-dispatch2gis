package be.district09.sf;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/dispatch")
public class DispatchResource {

    @GET()
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DispatchMessage> dispatch() {
        return DispatchMessage.GetUnsentMessages();
    }

//    @GET()
//    @Path("/sent/{code}")
//    public List<DispatchMessage> dispatch(@PathParam("code") String code) {
//        return DispatchMessage.GetSentMessages(code);
//    }

    @PUT()
    @Path("/ack/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DispatchMessage dispatch(@PathParam("id") int id) {
        return DispatchMessage.UpdateStatus(id, "ACK");
    }


}
