package boardello.service;

import boardello.http.EntityManagerUtil;
import boardello.model.Account;
import boardello.model.Board;
import boardello.model.Card;
import boardello.model.Checklist;
import boardello.model.ChecklistItem;
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
import javax.ws.rs.core.Response;

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
        welcome.setAccountId(act.getId());
        welcome.setName("Welcome Board");
        welcome.setSlug("welcome-board");
        welcome.setLastActivityAt(new Date());
        welcome.setLastViewedAt(new Date());
        em.persist(welcome);
        
        // Create some decks for the welcome board
        out.append("beginner deck...");
        Deck beginner = new Deck();
        beginner.setBoardId(welcome.getId());
        beginner.setName("Beginner");
        beginner.setPosition(1L);
        em.persist(beginner);
        
        out.append("intermediate deck...");
        Deck intermediate = new Deck();
        intermediate.setBoardId(welcome.getId());
        intermediate.setName("Intermediate");
        intermediate.setPosition(2L);
        em.persist(intermediate);
        
        out.append("advanced deck...\n");
        Deck advanced = new Deck();
        advanced.setBoardId(welcome.getId());
        advanced.setName("Advanced");
        advanced.setPosition(3L);
        em.persist(advanced);

        Label green = new Label();
        green.setColor("green");
        green.setName("Green label");
        green.setBoardId(welcome.getId());
        em.persist(green);
        
        Label red = new Label();
        red.setColor("red");
        red.setName("Red label");
        red.setBoardId(welcome.getId());
        em.persist(red);
        
        out.append("Adding cards to the beginners deck...\n");
        Card invite = new Card();
        invite.setDeckId(beginner.getId());
        invite.setName("Invite your team to this board using the Add Members button.");
        invite.setSlug("invite-your-team-to-this-board");
        invite.setPosition(1L);
        em.persist(invite);
        
        Card dragPeople = new Card();
        dragPeople.setDeckId(beginner.getId());
        dragPeople.setName("Drag people onto a card to indicate they're responsible for it.");
        dragPeople.setSlug("drag-people-onto-a-card");
        dragPeople.setDescription("This card has colored labels on it to be an example of what a card with labels looks like.  Does it render correctly or is it causing problems?  This is long text testing some formatting, sizing, wrapping, blah blah.");
        dragPeople.setPosition(2L);
        dragPeople.setLabelIds(new ArrayList<Long>());
        dragPeople.getLabelIds().add(green.getId());
        dragPeople.getLabelIds().add(red.getId());
        dragPeople.setDueDate(new Date());
        em.persist(dragPeople);
        
        Checklist tada1 = new Checklist();
        tada1.setCardId(dragPeople.getId());
        tada1.setName("Groceries");
        tada1.setPosition(1L);
        em.persist(tada1);
        
        String [] groceries = {"Eggs", "Milk", "Butter", "Potato Chips", "Soap", "Dog Food"};
        
        long pos = 1L;
        for(String item : groceries) {
            ChecklistItem cli = new ChecklistItem();
            cli.setChecklistId(tada1.getId());
            cli.setCompleted(Math.random() < 0.5 ? true : false);
            cli.setContent(item);
            cli.setPosition(pos++);
            em.persist(cli);
        }
    
        Checklist tada2 = new Checklist();
        tada2.setCardId(dragPeople.getId());
        tada2.setName("Cars To Buy");
        tada2.setPosition(2L);
        em.persist(tada2);
        
        String [] cars = {"Ford Flex", "Jeep Wrangler Unlimited", "Cadillac CTS-V", "Porsche 911"};
        
        pos = 1L;
        for(String item : cars) {
            ChecklistItem cli = new ChecklistItem();
            cli.setChecklistId(tada2.getId());
            cli.setCompleted(Math.random() < 0.5 ? true : false);
            cli.setContent(item);
            cli.setPosition(pos++);
            em.persist(cli);
        }
    
        
        out.append("Commit\n");
        tx.commit();
        
        out.append("Done!");
        
        return out.toString();
    }
    
}
