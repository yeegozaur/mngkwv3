package pl.edu.wat.shoeshop.service;


import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.shoeshop.repository.ShoeRepository;
import pl.edu.wat.shoeshop.repository.OwnerRepository;

@Service
@Slf4j
public class ScriptService {
    private final ShoeRepository shoeRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public ScriptService(ShoeRepository shoeRepository, OwnerRepository ownerRepository) {
        this.shoeRepository = shoeRepository;
        this.ownerRepository = ownerRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("shoeRepository", shoeRepository);
            bindings.putMember("ownerRepository", ownerRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}
