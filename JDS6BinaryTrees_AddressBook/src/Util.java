public class Util {
    public static double randomToRange(int range){
        return (Math.random() * range);
    }

    public static String[] approveCommand(String commandQuery) {
        String[] command = commandQuery.split("\\s+");
        return (command.length > 3)?  null:command;
    }
    public static Integer radixHashing(String position){
        Integer hashKey = 0;
        for (int i = 0; i < position.length(); i++)   hashKey = (hashKey * 3) + position.charAt(i);
        return hashKey;
    }
}
