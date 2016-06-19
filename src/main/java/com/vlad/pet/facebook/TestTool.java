package com.vlad.pet.facebook;

class TestTool {
    public static void main(String... args) {
        String empty = "";
        String nP = null;
        System.out.println("".equals(empty));
        System.out.println("".equals(null));
    }

    private static Contact getTestContact() {
        Contact contact = new Contact();
        contact.setFirstName("Vlad");
        contact.setLastName("Sereda");
        contact.setEmail("vlad.sereda.y@gmail.com");
        contact.setMobileNum("+380687044854");
        contact.setWorkNum("322223");
        return contact;
    }
}
