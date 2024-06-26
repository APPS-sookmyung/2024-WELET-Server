package WELLET.welletServer.card.service;

import WELLET.welletServer.card.Repository.CardRepository;
import WELLET.welletServer.card.domain.Card;
import WELLET.welletServer.card.dto.CardListResponse;
import WELLET.welletServer.card.dto.CardResponse;
import WELLET.welletServer.card.dto.CardSaveDto;
import WELLET.welletServer.card.dto.CardUpdateDto;
import WELLET.welletServer.card.exception.CardErrorCode;
import WELLET.welletServer.card.exception.CardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {
    private final CardRepository cardRepository;

    @Transactional
    public long saveCard (CardSaveDto dto) {
        Card card = Card.builder()
                .name(dto.getName())
                .position(dto.getPosition())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .tel(dto.getTel())
                .department(dto.getDepartment())
                .company(dto.getCompany())
                .address(dto.getAddress())
                .address(dto.getMemo())
                .build();
        return cardRepository.save(card).getId();
    }

    public List<CardListResponse> findAllCard() {
        List<Card> cardList = cardRepository.findAll();
        // Entity -> DTO
        return cardList.stream()
                .map(CardListResponse::toCardList)
                .toList();
    }

    public CardResponse findOne(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUNT));

        return CardResponse.toCardDto(card);
    }



    @Transactional
    public CardUpdateDto updateCard(Long cardId, CardUpdateDto dto) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUNT));

        card.updateCard(dto);
        return CardUpdateDto.toCardUpdateDto(card);
    }

    @Transactional
    public long deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardErrorCode.CARD_NOT_FOUNT));
        cardRepository.delete(card);
        return cardId;
    }
}
