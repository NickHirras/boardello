package boardello.service;

import boardello.http.EntityManagerUtil;
import boardello.model.Account;
import boardello.model.Board;
import boardello.model.Card;
import boardello.model.Deck;
import boardello.model.Label;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("dev")
public class DevService {
    
    @GET
    @Path("seed")
    @Produces({MediaType.TEXT_PLAIN})
    public String seed() {
        
        StringBuilder out = new StringBuilder();
        
        out.append("Creating entity manager...");
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        out.append("done!\n");
        
        out.append("Begin transaction\n");
        tx.begin();
        
        // Create an account for me.
        out.append("Create account for nick.smith@gmail.com\n");
        Account act = new Account();
        act.setEmail("nick.smith@gmail.com");
        act.setPasswordDigest("02210ea44a2aaf6d3282ffe20bb995cdcaffcda56e53370193dba32a938cff5f4a4daf95ca262f9c779f56449339fddd2ace92e0c552990b3b1cbaffe021e6b9");
        act.setFullName("Nicholas Smith");
        em.persist(act);
        
        // Create the welcome board.
        out.append("Create content for user: welcome board...");
        Board welcome = new Board();
        welcome.setAccount(act);
        welcome.setName("Welcome Board");
        welcome.setSlug("welcome-board");
        welcome.setLastActivityAt(new Date());
        welcome.setLastViewedAt(new Date());
        em.persist(welcome);
        
        // Create some decks for the welcome board
        out.append("beginner deck...");
        Deck beginner = new Deck();
        beginner.setBoard(welcome);
        beginner.setName("Beginner");
        beginner.setPosition(1L);
        em.persist(beginner);
        
        out.append("intermediate deck...");
        Deck intermediate = new Deck();
        intermediate.setBoard(welcome);
        intermediate.setName("Intermediate");
        intermediate.setPosition(2L);
        em.persist(intermediate);
        
        out.append("advanced deck...\n");
        Deck advanced = new Deck();
        advanced.setBoard(welcome);
        advanced.setName("Advanced");
        advanced.setPosition(3L);
        em.persist(advanced);

        Label green = new Label();
        green.setColor("green");
        green.setName("Green label");
        green.setBoard(welcome);
        em.persist(green);
        
        Label red = new Label();
        red.setColor("red");
        red.setName("Red label");
        red.setBoard(welcome);
        em.persist(red);
        
        out.append("Adding cards to the beginners deck...\n");
        Card invite = new Card();
        invite.setDeck(beginner);
        invite.setName("Invite your team to this board using the Add Members button.");
        invite.setSlug("invite-your-team-to-this-board-using-the-add-members-button");
        invite.setPosition(1L);
        em.persist(invite);
        
        Card dragPeople = new Card();
        dragPeople.setDeck(beginner);
        dragPeople.setName("Drag people onto a card to indicate they're responsible for it.");
        dragPeople.setSlug("drag-people-onto-a-card-to-indicate-theyre-responsible-for-it");
        dragPeople.setPosition(2L);
        dragPeople.setLabels(new ArrayList<Label>());
        dragPeople.getLabels().add(red);
        dragPeople.getLabels().add(green);
        em.persist(dragPeople);
        
        
        List<Card> begCards = new ArrayList<Card>();
        begCards.add(invite);
        begCards.add(dragPeople);
        beginner.setCards(begCards);
        em.merge(beginner);
        
        out.append("Commit\n");
        tx.commit();
        
        out.append("Done!");
        
        return out.toString();
    }
    
}
