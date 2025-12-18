package vn.kurisu.anime_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    EXISTS_EXCEPTION(1001, "Existed", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1002, "NOT FOUND", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1004, "Bạn không có quyền truy cập (Cần Admin)", HttpStatus.FORBIDDEN),

    // valid data
    INVALID_KEY(1001, "Lỗi validation chưa được định nghĩa", HttpStatus.BAD_REQUEST), // Lỗi mặc định
    USERNAME_INVALID(1003, "Username phải có ít nhất 3 ký tự", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password phải có ít nhất 8 ký tự", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1005, "Tuổi của bạn phải lớn hơn {min}", HttpStatus.BAD_REQUEST), // Demo lỗi có tham số
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
