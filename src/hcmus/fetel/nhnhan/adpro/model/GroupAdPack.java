package hcmus.fetel.nhnhan.adpro.model;

public class GroupAdPack {
    private int volume;
    private AdPackage adPackage;

    public GroupAdPack(int volume, AdPackage adPackage) {
        this.volume = volume;
        this.adPackage = adPackage;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public AdPackage getAdPackage() {
        return adPackage;
    }

    public void setAdPackage(AdPackage adPackage) {
        this.adPackage = adPackage;
    }

    @Override
    public String toString() {
        return "GroupAdPack [volume=" + volume + ", adPackage=" + adPackage + "]";
    }
}
