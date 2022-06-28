package out;

import java.util.ArrayList;
import java.util.StringJoiner;

abstract class SysFile {
  protected Folder up;
  private long id;

  public
  SysFile(Folder up, long id)
  {
    if (up != null)
      up.add(this);
    this.up = up;
    this.id = id;
  }

  @Override
  abstract public String toString();

  public void
  die()
  {
    up.remove(id);
  }

  public void
  mv(Folder up)
  {
    this.up.remove(id);
    if (up != null)
      up.add(this);
    this.up = up;
  }

  public boolean
  isEqual(long id)
  {
    return this.id == id;
  }
}

class Link extends SysFile {
  private Entry entry;
  
  public
  Link(Folder up, long id, Entry entry)
  {
    super(up, id);
    entry.ln(this);
    this.entry = entry;
  }

  @Override
  public String
  toString()
  {
    return up + "/(" + entry + ")";
  }
}

class Entry extends SysFile {
  private String name;
  private ArrayList<SysFile> links;

  public
  Entry(Folder up, long id, String name)
  {
    super(up, id);
    this.name = name;
    links = new ArrayList<SysFile>();
  }

  @Override
  public String
  toString()
  {
    if (up == null)
      return "/" + name;

    return up + "/" + name;
  }

  public void
  ln(SysFile f)
  {
    links.add(f);
  }

  @Override
  public void
  die()
  {
    super.die();
    for (SysFile f: links)
      f.die();
  }
}

class Folder extends Entry {
  private ArrayList<SysFile> contents;

  public
  Folder(Folder up, long id, String name)
  {
    super(up, id, name);
    contents = new ArrayList<SysFile>();
  }

  public void
  add(SysFile f)
  {
    contents.add(f);
  }

  public void
  remove(long id)
  {
    int i;
    for (i = 0; i < contents.size(); ++i)
      if (contents.get(i).isEqual(id))
        break;

    contents.remove(i);
  }

  public String
  ls()
  {
    StringJoiner fls = new StringJoiner("\n");
    fls.add(toString() + ":");
    for (SysFile f: contents)
      fls.add(f.toString());

    return fls.toString();
  }

  @Override
  public void
  die()
  {
    super.die();
    for (SysFile f: contents)
      f.die();
  }
}

class File extends Entry {
  private byte permission;

  public
  File(Folder up, long id, String name, byte permission)
  {
    super(up, id, name);
    this.permission = permission;
  }

  public boolean
  isRead()
  {
    return permission / 2 / 2 == 1;
  }

  public boolean
  isWrite()
  {
    return permission / 2 % 2 == 1;
  }

  public boolean
  isExec()
  {
    return permission % 2 == 1;
  }
}
