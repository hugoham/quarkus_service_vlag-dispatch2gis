package be.district09.sf;

import io.quarkus.agroal.DataSource;
import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vlag_dispatch")
@PersistenceUnit("target_db")
public class DispatchMessage extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public int id;

    public String message;

    public Date timestamp;

    public String status;

//    public String code;

    @Transactional
    public static List<DispatchMessage> GetUnsentMessages() {

        System.out.println("GetUnsentMessages");
        List<DispatchMessage> result = list("status != ?1", "ACK");

        System.out.println("Updating status and code");
        var newCode = UUID.randomUUID().toString();
        result.forEach(d -> {
            d.status = "SENT";
//            d.code = newCode;
            d.persistAndFlush();
        });

        return list("code", newCode);
    }

//    public static List<DispatchMessage> GetSentMessages(String sentCode) {
//        return list("code = ?1 and status = ?2", sentCode,  "SENT");
//    }

    @Transactional
    public static DispatchMessage UpdateStatus(int id, String status) {
        DispatchMessage d = findById(id);
        d.status = status;
        d.persistAndFlush();
        return d;
    }
}

