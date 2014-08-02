/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.service.dto;

import boardello.model.Board;
import boardello.model.Card;
import boardello.model.Checklist;
import boardello.model.ChecklistItem;
import boardello.model.Deck;
import boardello.model.Label;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicholas
 */
@XmlRootElement
public class CardContents {
    private Board board;
    private Deck deck;
    private Card card;
    private List<Label> labels;
    private List<Checklist> checklists;
    private List<ChecklistItem> checklistItems;
    
    public CardContents() {
        
    }
    
    public CardContents(Board board, Deck deck, Card card, List<Label> labels, List<Checklist> checklists, List<ChecklistItem> checklistItems) {
        this.board = board;
        this.deck = deck;
        this.card = card;
        this.labels = labels;
        this.checklists = checklists;
        this.checklistItems = checklistItems;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public List<ChecklistItem> getChecklistItems() {
        return checklistItems;
    }

    public void setChecklistItems(List<ChecklistItem> checklistItems) {
        this.checklistItems = checklistItems;
    }
         
}
