package design.UniqueEmails;

import java.util.HashSet;
import java.util.Set;

public class uniqueEmails {

    public static int numUniqueEmails(String[] emails) {

        Set<String> uniqueEmails = new HashSet<>();

        for(String s:emails){
            int indexAt = s.indexOf('@')<0?s.length():s.indexOf('@');
            String local = s.substring(0,indexAt);
            String domain = s.substring(indexAt);
            local= local.replaceAll("\\.","");
            local=local.substring(0,local.indexOf('+')<0?local.length():local.indexOf('+'));
            uniqueEmails.add(local+domain);

            String g = "abc";
            g.startsWith("");
        }

        return uniqueEmails.size();

    }

    public static void main(String[] args) {
        String[] emails = new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println(numUniqueEmails(emails));
    }
}
