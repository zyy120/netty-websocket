package com.zyy.collection;

import java.util.HashMap;import java.util.Map;
/** *第一次创建ParmaterMap实例是没有锁。 *该类继承了HashMap,包含了一个locked成员变量 * @author Jerry */
public final class LockedMap extends HashMap {
    // ----------------------------------------------------------- 构造函数 /** * 构造一个新的map,容量是默认值,加载因子也是默认值 */ public LockedMap() { super(); } /** * 构造一个新的map,容量值是一个具体的初始容量,加载因子是默认值 * * @param initialCapacity 这个map的初始容量 */
public LockedMap(int initialCapacity) { super(initialCapacity); }
/** * 构造一个新的map,容量值是一个具体的初始容量,加载因子是loadeFactor * * @param initialCapacity 这个map的初始容量 *
 *  @param loadFactor 这个map的加载因子 */
public LockedMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor); }
    /** * 复制一个map,给新的map * * @param map Map的类容复制到新的Map中 */
    public LockedMap(Map map) { super(map);
    }
    // ------------------------------------------------------------- 类的成员变量 /** * 这个Map当前锁的状态 */

 private boolean locked = false; //------------------------------------------------------------get和set方法
// /** * Return 这个map锁的状态. */
public boolean isLocked() { return (this.locked); }
/** * 设置这个map锁的状态 * * @param locked 一个新锁的状态 */
public void setLocked(boolean locked) { this.locked = locked; }
// --------------------------------------------------------- Public Methods
// /** * 移除这个map的所有键值对. * * @exception IllegalStateException 如果这个Map被锁住 */
public void clear() {
    if (locked) throw new IllegalStateException ("LockedMap.locked"); super.clear(); }
    /** * 在这个Map中具体的值与具体的键相关联。如果Map里面种包含了这个key,那么旧值就会被 * 替换
     *  * * @param key Key是与具体的值相关联 * @param value 值必须与具体的key相关联 * *
     *  @return 具体key与之关联的value * * @exception IllegalStateException 如果这个Map被锁住 */
    public Object put(Object key, Object value) {
        if (locked) throw new IllegalStateException ("LockedMap.locked");
        return (super.put(key, value));
    }
    /** * 把指定的map中的所有键值对复制到这个map中
     * * @param map 所有的键值对存放在这个map中 * * @exception IllegalStateException 如果这个Map被锁住 */
    public void putAll(Map map) {
        if (locked) throw new IllegalStateException ("LockedMap.locked"); super.putAll(map);
    }
    /** * 如果存在就会移除key相关联的value值. * * @param key * * @exception IllegalStateException 如果这个Map被锁住 */
    public Object remove(Object key) {
        if (locked) throw new IllegalStateException ("LockedMap.locked"); return (super.remove(key));
    }
}
