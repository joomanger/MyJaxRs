/*

 */
package beans.so.config.rules;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ConversationScoped
public class RuleConversation implements Serializable {

    @Inject
    private Conversation conversation;
    
    private Long rule_id;

    public void initConversation() {
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            System.out.println("begin conv "+rule_id);
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        System.out.println("end conv");
    }
    
    public String openView(){
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {
            System.out.println("begin conv "+rule_id);
            conversation.begin();
        }
        return "openRule?faces-redirect=true";
    }

    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        
        this.rule_id = rule_id;
        System.out.println(rule_id);
    }
    
}
