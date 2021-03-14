package edge.dto;

import edge.enums.Status;

import javax.validation.constraints.NotNull;

public class StatusDTO {
    @NotNull (message = "A status is required")
    private Status status;

    public StatusDTO(  @NotNull (message = "A status is required") Status status) {
        this.status = status;
    }
    public StatusDTO() {
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}

