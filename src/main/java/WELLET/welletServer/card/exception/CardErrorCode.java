package WELLET.welletServer.card.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardErrorCode {
    CARD_NOT_FOUNT("명함을 찾을 수 없습니다.");

    private final String message;
}
