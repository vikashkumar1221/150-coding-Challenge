class Solution {
    public int calPoints(String[] operations) {
        List<Integer> lst = new ArrayList<>();

        for (String str : operations) {
            if (str.equals("C")) {
                lst.remove(lst.size() - 1);
            } else if (str.equals("D")) {
                lst.add(lst.get(lst.size() - 1) * 2);
            } else if (str.equals("+")) {
                lst.add(lst.get(lst.size() - 1) + lst.get(lst.size() - 2));
            } else {
                lst.add(Integer.valueOf(str));
            }
        }

        return lst.stream().mapToInt(i -> i).sum();

    }
}