package twopointers;

import java.util.*;

public class ThreeSum {
      public List<List<Integer>> tripletsWithSum0(List<Integer> nums){
          Map<Integer,Integer> counter = new HashMap<>();
          for(int item: nums){
              counter.merge(item,1,Integer::sum);
          }
          List<Integer> uniqeList = new ArrayList<>(counter.keySet());
          Collections.sort(uniqeList);

          List<List<Integer>> result = new ArrayList<>();
          for(int i=0;i<uniqeList.size();i++){
              int firstItem = uniqeList.get(i);
              counter.merge(firstItem,-1,Integer::sum);
              for(int j=i;j<uniqeList.size();i++){
                  int secItem = uniqeList.get(j);
                  if(counter.get(secItem)<=0 )continue;

                  int thirdItem = -firstItem - secItem;
                  if(thirdItem<secItem) break;

                  counter.merge(secItem,-1,Integer::sum);
                  if(counter.getOrDefault(thirdItem,0)<=0){
                      counter.merge(secItem,1,Integer::sum);
                      continue;
                  }
                  List<Integer> tlist = new ArrayList<>();
                  tlist.add(firstItem);
                  tlist.add(secItem);
                  tlist.add(thirdItem);
                  result.add(tlist);
                  counter.merge(secItem,1, Integer::sum);
                  
              }
          }
          return result;
      }
}
