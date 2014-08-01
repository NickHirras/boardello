/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.service.dto;

import boardello.model.Account;
import boardello.model.Board;
import boardello.model.Card;
import boardello.model.Deck;
import boardello.model.Label;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicholas
 */
@XmlRootElement
public class BoardContents {
    private Account account;
    private Board board;
    private List<Label> labels;
    private List<Deck> decks;
    private List<Card> cards;

    public BoardContents() {
        
    }
    
    public BoardContents(Account account, Deck deck, Board board, List<Label> labels, List<Deck> decks, List<Card> cards) {
        this.account = account;
        this.board = board;
        this.labels = labels;
        this.decks = decks;
        this.cards = cards;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }
    
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    
    
}
