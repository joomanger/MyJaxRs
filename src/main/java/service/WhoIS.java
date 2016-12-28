package service;

import java.util.Date;

/**
 *
 * @author savin
 */
public interface WhoIS {

    Date getCreationDate();

    void setCreationDate(Date creationDate);

    Long getCreatedBy();

    void setCreatedBy(Long createdBy);

    Date getLastUpdateDate();

    void setLastUpdateDate(Date lastUpdateDate);

    Long getLastUpdatedBy();

    void setLastUpdatedBy(Long lastUpdatedBy);
}
