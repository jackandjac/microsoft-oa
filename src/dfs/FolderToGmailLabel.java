package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Below is a coding problem I was asked during a Google technical phone screen:
 *
 * Question: Convert an array of email folder objects to an array of Gmail label strings.
 *
 * // Sample input:
 *
 * folders = [
 *     {id: 27, parentId: 15, name: 'projects'},
 *     {id: 81, parentId: 27, name: 'novel'},
 *     {id: 15, parentId: -1, name: 'personal'}, // a parentId of -1 means root, some of the variant using 0 to represent root.
 *     {id: 35, parentId: 27, name: 'blog'},
 * ]
 *
 * // Sample output:
 *
 * labels = [
 *     displayName: 'projects', path: 'personal/projects',
 *     displayName: 'novel', path:'personal/projects/novel',
 *     displayName: 'personal', path:'personal',
 *     displayName: 'blog', path: 'personal/projects/blog',
 * ]
 *
 * */
public class FolderToGmailLabel {
    List<Label> folderToLabels(List<Folder> folders){
        Map<Integer, Folder> idFolderMap = new HashMap<>();
        for(Folder folder:folders){
            idFolderMap.put(folder.id, folder);
        }
        List<Label> results = new ArrayList<>();

        for(Folder folder:folders){
            Label label = new Label(folder.name, materializePath(folder.id, idFolderMap));
            results.add(label);
        }
        return results;
    }

    private String[] breakPath(Label label){
        return label.path.split("/");
    }

    private String materializePath(int id, Map<Integer,Folder> idFolderMap){
        if(id == -1) return "";
        String name = idFolderMap.get(id).name;
        return idFolderMap.get(id).parentId == -1 ? name: materializePath(idFolderMap.get(id).parentId, idFolderMap) +"/" + name;
    }
    List<Folder> labelToFolders(List<Label> labels){
        Dir root = new Dir();
        for(Label label:labels){
            String[] paths = breakPath(label);

        }
        // to do
        return null;
    }
}
class Dir{
    private static int autoId = -1;
    public String name;
    public Map<String, Dir> entries;
    public int id;
    public Dir(){
        this(null);
    }
    public Dir(String name){
        this.name = name;
        entries = new HashMap<>();
        this.id = autoId++;
    }
}

class Folder {
    public int id;
    public int parentId;
    public String name;
    public Folder(int id, int parentId, String name){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
    public Folder(){}
}

class Label {
    public String displayName;
    public String path;
    public Label(String displayName, String path){
        this.displayName = displayName;
        this.path = path;
    }
    public Label(){}
}
