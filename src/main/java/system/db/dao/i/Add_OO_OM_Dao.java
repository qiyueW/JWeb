package system.db.dao.i;

import java.util.List;

/**
 *
 * @author wangchunzi
 */
public interface Add_OO_OM_Dao {

    public <O, O2> int[] add_OO(O o, O2... o2);

    public <O, M> int[] add_OM(O obj, List<M>... m);
    
    

}
