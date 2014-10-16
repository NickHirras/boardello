/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardello.http;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;

/**
 *
 *
 *
 * @author Prasobh.K
 */
public class EntityManagerFilter implements Filter {

  private static EntityManagerFactory entityManagerFactory = null;

  @Override
  public void init(FilterConfig fc) throws ServletException {

    destroy();

    entityManagerFactory = Persistence.createEntityManagerFactory("boardelloPU");

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {

    EntityManager em = null;

    try {

      em = entityManagerFactory.createEntityManager();

      EntityManagerUtil.ENTITY_MANAGERS.set(em);

      chain.doFilter(request, response);

      EntityManagerUtil.ENTITY_MANAGERS.remove();

    } finally {

      try {

        if (em != null) {

          em.close();

        }

      } catch (Throwable t) {

        System.out.println("Error " + t);

      }
    }
  }

  @Override
  public void destroy() {

    {

      if (entityManagerFactory != null) {

        entityManagerFactory.close();
      }
    }
  }
}
