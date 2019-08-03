package pl.coderslab.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.dto.ReserveAsk;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonSession {
    private String email;

    private ReserveAsk reserveAsk;

    public ReserveAsk getReserveAsk() {
        return reserveAsk;
    }

    public void setReserveAsk(ReserveAsk reserveAsk) {
        this.reserveAsk = reserveAsk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
