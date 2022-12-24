package com.maomo.weiyunsuan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//数组中的数满足一个条件：只有一个数出现了奇数次，其他数都出现了偶数次
//请找出出现了奇数次的数
public class Code01_1jishu {
    public static int findJiShuTimesNum(int[] arr){
        int ans = 0;
        for(int num : arr){
            ans ^= num;
        }
        return ans;
    }

    public static int test(int[] arr){
        Map<Integer,Integer> numMap = new HashMap<>();
        for(int num : arr){
            if(numMap.containsKey(num)){
                int value = numMap.get(num);
                value++;
                numMap.put(num,value);
            }else{
                numMap.put(num,1);
            }
        }
        for(int num : numMap.keySet()){
            int value = numMap.get(num);
            if(value %2 !=0){
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int maxLength = 15;
        int maxValue = 100;
        int testTimes = 10;

        for(int i=0; i< testTimes; i++){
            int[] arr = getRandomArray(maxLength,maxValue);
            int num1 = findJiShuTimesNum(arr);
            int num2 = test(arr);
            if(num1 != num2){
                System.out.println("出错了");
                break;
            }else{
                System.out.println("成功了");
            }
        }

    }

    private static int fillOushuTimes(int[] arr, int lastLength,Set<Integer> numSet,int maxValue){
        int oushuNum = getRandomNum(numSet,maxValue);
        int oushuTimes = getOuShuRamdonLength(arr.length-lastLength);
        for(int i=lastLength; i<arr.length; i++){
            arr[i] = oushuNum;
        }
        return oushuTimes;
    }

    private static void swap(int[] arr, int i, int j){
        if(i == j){
            return ;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private static int[] getRandomArray(int maxLength, int maxValue){
        //数组的长度范围[1,maxLength]
        int length = getJiShuRamdonLength(maxLength);
        //Set对象保存数组中的数，防止随机重复
        Set<Integer> numSet = new HashSet<>();
        //生成出现奇数次的数
        int jishuNum = getRandomNum(numSet,maxValue);
        int[] arr = new int[length];
        //将某个数设置为奇数次

        int i = 0;
        int jishuTimes = getJiShuRamdonLength(length);
        for(;i< jishuTimes; i++){
            arr[i] = jishuNum;
        }
        //其他的数出现偶数次

        do{
            int oushuTimes = fillOushuTimes(arr,i,numSet,maxValue);
            i += oushuTimes;
        }while (i<arr.length);
        //打乱顺序
        for(int index = 0; index < arr.length; index ++){
            int swapIndex = (int)(Math.random() * arr.length);
            swap(arr,index,swapIndex);
        }
        return arr;
    }

    private static int getJiShuRamdonLength(int maxLength){
        int length;
        do{
            length = (int)(Math.random() * maxLength)+1;
        }while((length %2) == 0);
        return length;
    }

    private static int getOuShuRamdonLength(int maxLength){
        int length;
        do{
            length = (int)(Math.random() * maxLength)+1;
        }while((length %2) != 0);
        return length;
    }


    //生成Set里不重复的随机数，最大值不超过maxValue,并将随机数加入set
    private static int getRandomNum(Set<Integer> numSet,int maxValue){
        int num ;
        do{
            num = (int)(Math.random() * maxValue);
        }while(numSet.contains(num));
        numSet.add(num);
        return num;
    }
}
