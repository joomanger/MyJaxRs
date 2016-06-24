package lookup.entities;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import lookup.beans.LookupEJB;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author savin
 */
@RunWith(CdiRunner.class)
public class LookupTest {

    @Inject
    private LookupEJB ejb;

    @Produces
    private final EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
    private EntityTransaction tm;

    public LookupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tm = em.getTransaction();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addLookupItem method, of class Lookup.
     */
    @Test
    @InRequestScope
    public void createLookup() {
        Lookup lookup = new Lookup();
        lookup.setActiveStatus(Boolean.TRUE);
        lookup.setName("name");
        lookup.setDescription("asasas");
        LookupItem li = new LookupItem();
        li.setActiveStatus(Boolean.TRUE);
        li.setValuez("valuez");
        li.setValuezDescription("asasasasasasasas");
        lookup.addLookupItem(li);

        String status = ejb.create(lookup);
        ejb.sendMessage(status, "Справочник создан успешно");
        if (status.equals(ejb.SUCCESSFUL)) {
            System.out.println("lookups");
        } else {
            System.out.println("error " + status);
        }
    }

}
