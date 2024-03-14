package codes.c06tree.p1609;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThroneInheritance {
    private ThroneMem throne;
    private HashMap<String, ThroneMem> addressBook;

    public ThroneInheritance(String kingName) {
        throne = new ThroneMem(kingName);
        addressBook = new HashMap<>();
        addressBook.put(kingName, throne);
    }
    
    public void birth(String parentName, String childName) {
        ThroneMem parent = addressBook.get(parentName);
        ThroneMem child = new ThroneMem(childName);
        parent.children.add(child);
        addressBook.put(childName, child);
    }
    
    public void death(String name) {
        ThroneMem mem = addressBook.get(name);
        mem.live = false;
    }
    
    public List<String> getInheritanceOrder() {
        List<String> res = new ArrayList<>();
        preOrder(res, throne);
        return res;
    }

    private void preOrder(List<String> res, ThroneMem t){
        visit(res, t);
        for(ThroneMem tm: t.children)
            preOrder(res, tm);
    }
    private void visit(List<String> res, ThroneMem t){
        if (t.live)
            res.add(t.name);
    }
}

class ThroneMem{
    String name;
    boolean live;
    List<ThroneMem> children;

    public ThroneMem(String name){
        this.name = name;
        live = true;
        children = new ArrayList<>();
    }
}

// 题目本质为先序遍历多叉树, 得到遍历序列(对于失效节点不予输出)
// 然而题目中还包含 birth, dead 等等操作, 通常来讲:
//      它们都需要先按值查找到相应节点, 然后执行插入(删除)操作
//      这是很耗时的
// 考虑到每个成员的名字都是不一样的, 可以维持一个 hashmap
// king(0), andy(1), bob(2), cath(3), matt(4), alex(5), asha(6)
// 



