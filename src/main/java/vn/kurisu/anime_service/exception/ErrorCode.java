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
    UNAUTHORIZED(1004, "Bạn không có quyền truy cập (Cần Admin)", HttpStatus.FORBIDDEN);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
