package com.praneethgurramgmail.eceinventory;

/**
 * Created by praneeth on 4/5/2017.
 */

public class Items {

    private String Owner, OrgnCode, OrgnTitle, Room, Bldg, SortRoom, Ptag, Manufacturer, Model, SN, Description, PO, AcqDate, Amt, Ownership, SchevYear,
            TagType, AssetType, AtypTitle, Condition, LastInvDate, Designation;
    public Items(String Owner, String OrgnCode, String OrgnTitle, String Room, String Bldg,String SortRoom, String Ptag,  String Manufacturer, String Model, String SN, String Description, String PO, String AcqDate, String Amt, String Ownership, String SchevYear, String TagType,
    String AssetType, String AtypTitle, String Condition, String LastInvDate, String Designation ){
        this.setOwner(Owner);
        this.setOrgnCode(OrgnCode);
        this.setOrgnTitle(OrgnTitle);
        this.setRoom(Room);
        this.setBldg(Bldg);
        this.setSortRoom(SortRoom);
        this.setPtag(Ptag);
        this.setManufacturer(Manufacturer);
        this.setModel(Model);
        this.setSN(SN);
        this.setDescription(Description);
        this.setPO(PO);
        this.setAcqDate(AcqDate);
        this.setAmt(Amt);
        this.setOwnership(Ownership);
        this.setSchevYear(SchevYear);
        this.setTagType(TagType);
        this.setAssetType(AssetType);
        this.setAtypTitle(AtypTitle);
        this.setCondition(Condition);
        this.setLastInvDate(LastInvDate);
        this.setDesignation(Designation);

    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getOrgnCode() {
        return OrgnCode;
    }

    public void setOrgnCode(String orgnCode) {
        OrgnCode = orgnCode;
    }

    public String getOrgnTitle() {
        return OrgnTitle;
    }

    public void setOrgnTitle(String orgnTitle) {
        OrgnTitle = orgnTitle;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getBldg() {
        return Bldg;
    }

    public void setBldg(String bldg) {
        Bldg = bldg;
    }

    public String getSortRoom() {
        return SortRoom;
    }

    public void setSortRoom(String sortRoom) {
        SortRoom = sortRoom;
    }

    public String getPtag() {
        return Ptag;
    }

    public void setPtag(String ptag) {
        Ptag = ptag;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPO() {
        return PO;
    }

    public void setPO(String PO) {
        this.PO = PO;
    }

    public String getAcqDate() {
        return AcqDate;
    }

    public void setAcqDate(String acqDate) {
        AcqDate = acqDate;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public String getOwnership() {
        return Ownership;
    }

    public void setOwnership(String ownership) {
        Ownership = ownership;
    }

    public String getSchevYear() {
        return SchevYear;
    }

    public void setSchevYear(String schevYear) {
        SchevYear = schevYear;
    }

    public String getTagType() {
        return TagType;
    }

    public void setTagType(String tagType) {
        TagType = tagType;
    }

    public String getAssetType() {
        return AssetType;
    }

    public void setAssetType(String assetType) {
        AssetType = assetType;
    }

    public String getAtypTitle() {
        return AtypTitle;
    }

    public void setAtypTitle(String atypTitle) {
        AtypTitle = atypTitle;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getLastInvDate() {
        return LastInvDate;
    }

    public void setLastInvDate(String lastInvDate) {
        LastInvDate = lastInvDate;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }
}
