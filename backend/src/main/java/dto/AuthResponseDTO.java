package dto;

public class AuthResponseDTO {

    public String token;
    public String rol;

    public AuthResponseDTO(
            String token,
            String rol) {

        this.token = token;
        this.rol = rol;
    }
}