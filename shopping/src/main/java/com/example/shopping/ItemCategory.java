package com.example.shopping;

public enum ItemCategory {
    Electronics,
    Fashion,
    Kitchen,
    Health,
    Beauty,
    Books,
    Sports,
    Toys,
    Games;

    public String getCategoryName() {
        return this.name();
    }
    public static Object getCategoryfromName(String s) {
        try {
            return ItemCategory.valueOf(s);
        } catch (IllegalArgumentException e) {
            // Nếu tên chuỗi không khớp với bất kỳ giá trị enum nào, trả về null hoặc xử lý lỗi
            return null;
        }
    }
}
