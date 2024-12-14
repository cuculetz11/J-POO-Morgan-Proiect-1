package org.poo.utils.factory;

import org.poo.entities.card.Card;
import org.poo.entities.card.ClassicCard;
import org.poo.entities.card.OneTimePayCard;
import org.poo.fileio.CommandInput;

public class CardFactory {
    public static Card getCard(final CommandInput input) {
        return switch (input.getCommand()) {
            case "createCard" -> new ClassicCard();
            case "createOneTimeCard" -> new OneTimePayCard();
            default -> null;
        };
    }
}
