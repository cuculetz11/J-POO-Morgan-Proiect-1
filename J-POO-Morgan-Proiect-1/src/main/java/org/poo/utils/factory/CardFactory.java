package org.poo.utils.factory;

import org.poo.entities.card.Card;
import org.poo.entities.card.ClassicCard;
import org.poo.entities.card.OneTimePayCard;
import org.poo.fileio.CommandInput;

public class CardFactory {
    public static Card getCard(CommandInput input) {
        switch (input.getCommand()) {
            case "createCard":
                return new ClassicCard();
            case "createOneTimeCard":
                return new OneTimePayCard();
            default:
                return null;
        }
    }
}
