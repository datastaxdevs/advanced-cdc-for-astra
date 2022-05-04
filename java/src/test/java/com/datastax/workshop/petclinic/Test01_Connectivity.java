package test.java.com.datastax.workshop.petclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import main.java.com.datastax.workshop.petclinic.PetClinicApplication;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.astra.sdk.databases.domain.Database;
import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootTest(classes=PetClinicApplication.class)
public class Test01_Connectivity {

    @Autowired
    private AstraClient astraClient;
    
    @Autowired
    private CqlSession cqlSession;
    
    @Test
    void should_display_cqlSession() {
        System.out.println("== CQL_SESSION ==");
        System.out.println("+ Your Keyspace: " + cqlSession.getKeyspace().get());
        System.out.println("+ Vet Specialty: ");
        // cqlSession.execute("select values "
        //         + "from petclinic_reference_lists "
        //         + "where list_name = 'vet_specialty'")
        //         .all().stream()
        //         .forEach(row -> System.out.println(row.getSet("values", String.class)));
    }
    
    @Test
    void should_display_astraClient() {
        System.out.println("== ASTRA ==");
        System.out.println("+ Your OrganizationID: " + astraClient.apiDevopsOrganizations().organizationId());
        System.out.println("+ Your Databases: ");
        astraClient.apiDevopsDatabases()
                   .databasesNonTerminated()
                   .forEach(this::displayDB);
    }
    
    private void displayDB(Database db) {
        System.out.println(db.getInfo().getName() + "\t : id=" + db.getId() + ", region=" + db.getInfo().getRegion());
    }
    
}