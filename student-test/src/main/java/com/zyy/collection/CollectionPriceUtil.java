package com.zyy.collection;

import com.yunji.scs.price.priceapplication.vo.PriceApplicationGeneralVo;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
* @Author  zsf
* @Description //只限制于价格系统  通用接口的最新返回
* @Date 2018/10/29 14:34
* @Param
* @return
*/
public class CollectionPriceUtil {
    /**
     * 运用PropertyUtils取得bean的值，并根据keyName归类
     *
     * @param list
     *            List beans
     * @param keyName
     *            需要归类的bean的属性名称
     * @return LinkedHashMap<String, List>,有顺序的map<br>
     *         map的key为需要归类的bean的属性名+"#"+对应的属性值：eg："class#312"<br>
     *         value为List<bean><br>   做了排序根据id
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */

    public static LinkedHashMap<String, List> classify(List list, String keyName)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        LinkedHashMap<String, List> target = new LinkedHashMap();
        for (Object obj : list) {
            // 取得bean需要归类的属性（keyName）的值，不做类型转换
            Object oKeyValue = PropertyUtils.getProperty(obj, keyName);
            String keyValue = keyName + "#" + String.valueOf(oKeyValue);
            if (!target.containsKey(keyValue)) {
                // 如果map中没有归类key值，则添加key值和相应的list
                ArrayList keyList = new ArrayList();
                keyList.add(obj);

                if(keyList!=null && keyList.size()>1){
                    Collections.sort(keyList, new Comparator<PriceApplicationGeneralVo>() {
                        public int compare(PriceApplicationGeneralVo o1, PriceApplicationGeneralVo o2) {
                            try {

                                if (o2.getId() > o1.getId()) return 1;
                                if (o2.getId() < o1.getId()) return -1;
                                if (o2.getId() == o1.getId()) return 0;

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                }
                target.put(keyValue, keyList);

            } else {
                // 如果有归类key值，则在相应的list中添加这个bean
                ArrayList keyList = (ArrayList) target.get(keyValue);
                keyList.add(obj);
                if(keyList!=null && keyList.size()>1){
                    Collections.sort(keyList, new Comparator<PriceApplicationGeneralVo>() {
                        public int compare(PriceApplicationGeneralVo o1, PriceApplicationGeneralVo o2) {
                            try {

                                if (o2.getId() > o1.getId()) return 1;
                                if (o2.getId() < o1.getId()) return -1;
                                if (o2.getId() == o1.getId()) return 0;

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                }
            }
        }
        return target;
    }

    /**
     *
     * 将归类的Map<String, List>按照 keyName归类，并用index控制递归。<br>
     * 因为直接调用没有意义，这个方法为private，
     *
     * @param mocl
     *            map of classified list<br>
     *            也就是运用方法<br>
     *            LinkedHashMap<String, List> classify(List list, String
     *            keyName)<br>
     *            将list归类成的map<br>
     *
     * @param index
     *            用条件 index < keyNames.length控制递归
     *
     * @param keyNames
     *            需要归类的bean的属性名称
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */

    private static LinkedHashMap<String, Map> classify(Map<String, List> mocl, int index,
                                                       String... keyNames) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        // 单步理解：target是函数参数Map<String, List> mocl再次归类成的LinkedHashMap<String,Map>
        // 递归到最后这个是最终归类好的map
        LinkedHashMap<String, Map> target = new LinkedHashMap();
        // 控制递归条件，起始的index应该总是1。
        if (index < keyNames.length) {
            // swap用来保存参数index的值，这是最容易出错的一个地方
            // 用它保证：在参数Map<String, List> mocl层面循环时用相同的index参数值。
            int swap = index;
            for (Map.Entry<String, List> entry : mocl.entrySet()) {
                String mocl_key = entry.getKey();
                List mocl_list = entry.getValue();
                // 将List<bean>再次归类
                LinkedHashMap<String, List> _mocl = classify(mocl_list, keyNames[index]);
                // 如果index达到了数组的最后一个，一定是List<bean>转map，递归结束
                if (index == keyNames.length - 1) {
                    target.put(mocl_key, _mocl);
                } else {
                    // 将List<bean>转map得到的_mocl，再次归类
                    // _mocm 为map of classified map的简称
                    LinkedHashMap<String, Map> _mocm = classify(_mocl, ++index, keyNames);
                    target.put(mocl_key, _mocm);
                }
                index = swap;
            }
        }
        return target;
    }

    /**
     * 将Map<String, List> map按照bean需要归类的属性名keyName归类
     *
     * @param map
     *            map of classified list<br>
     *            list归类成的map
     * @param keyName
     *            bean需要归类的属性名
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */

    public static LinkedHashMap<String, Map> classifyMap(Map<String, List> map, String keyName)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        LinkedHashMap<String, Map> target = new LinkedHashMap();
        for (Map.Entry<String, List> entry : map.entrySet()) {
            List map_list = entry.getValue();
            String map_key = entry.getKey();
            LinkedHashMap<String, List> keyMap = classify(map_list, keyName);
            target.put(map_key, keyMap);
        }
        return target;
    }

    /**
     * 将List<bean> 按照指定的bean的属性进行归类,keyNames的先后顺序会影响归类结果。<br>
     * eg:一个学生列表，按照班级和性别归类<br>
     * Map map = CollectionUtils.classifyList(studentList, "classId","sex");<br>
     *
     * @param list
     *            List beans
     * @param keyNames
     *            数组包含需要归类的bean的属性名称
     * @return 归类的有顺序的树状map<br>
     *         map的key为需要归类的bean的属性名+"#"+对应的属性值：eg："class#312"<br>
     *         map的值为List或者map
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static LinkedHashMap classifyList(List list, String... keyNames)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (keyNames == null || keyNames.length == 0)
            return null;
        if (keyNames.length == 1)
            return classify(list, keyNames[0]);
        else
            return classify(classify(list, keyNames[0]), 1, keyNames);
    }
}
