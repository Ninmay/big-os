package out;

public class Main {
  public static void main(String[] args) {
    System.out.println("Junk gets deleted and doesn't show up.");
    Folder f1 = new Folder(null, 1, "home");
    Folder f21 = new Folder(f1, 2, "Build");
    Folder f31 = new Folder(f21, 3, "st");
    Folder f32 = new Folder(f21, 4, "dwm");
    Folder f33 = new Folder(f21, 5, "dmenu");
    Folder f34 = new Folder(f21, 6, "junk");
    f34.die();
    System.out.println(f21.ls());
    System.out.println("------------------------------");

    System.out.println("Drawings folder gets moved to Build folder.");
    Folder f22 = new Folder(f1, 7, "Media");
    Folder f55 = new Folder(f22, 55, "Code");
    Folder f35 = new Folder(f22, 8, "Drawings");
    f35.mv(f21);
    System.out.println(f22.ls());
    System.out.println(f21.ls());
    System.out.println(f32.ls());
    System.out.println("------------------------------");
    f35.die();

    System.out.println("Introduce files to the Build folder.");
    File p1 = new File(f31, 9, "st.c", (byte)4);
    File p2 = new File(f31, 10, "st.h", (byte)6);
    File p3 = new File(f31, 11, "Makefile", (byte)6);
    File p4 = new File(f32, 12, "dwm.c", (byte)6);
    File p5 = new File(f32, 13, "dwm.h", (byte)6);
    File p6 = new File(f32, 14, "Makefile", (byte)6);
    File p7 = new File(f32, 15, "dwm", (byte)7);
    System.out.println(f31.ls());
    System.out.println(f32.ls());
    System.out.println("------------------------------");

    System.out.println("Files have attributes.");
    if (!p1.isWrite())
      System.out.println(p1 + " is not writeable.");
    if (!p4.isExec())
      System.out.println(p4 + " is not executable.");
    if (p7.isExec())
      System.out.println(p7 + " is executable.");
    System.out.println("------------------------------");

    System.out.println("We make a Desktop folder filled with links.");
    Folder d = new Folder(f1, 16, "Desktop");
    Link l1 = new Link(d, 17, p1);
    Link l2 = new Link(d, 18, p4);
    Link l3 = new Link(d, 19, p7);
    Link l4 = new Link(d, 19, f55);
    System.out.println(d.ls());
    System.out.println("------------------------------");

    System.out.println("dwm.c is deleted, st.c is moved to dwm folder.");
    p4.die();
    p1.mv(f32);
    System.out.println(d.ls());
    System.out.println("------------------------------");
  }
}
