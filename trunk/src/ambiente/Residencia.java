package ambiente;

import framework.FIPA.ElementID;
import framework.environment.MTS_Environment;

public class Residencia extends MTS_Environment {
    
    public Residencia(ElementID aid) {
        super(aid);
    }

    @Override
    public String toString() {
        return super.getEnvironmentName();
    }
    
}

