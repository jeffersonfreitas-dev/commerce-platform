export interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
  originalPrice?: number;
  imageUrl: string;
  gallery?: string[];
  category: string;
  stock: number;
  rating: number;
  reviews: number;
  tags?: string[];
  createdAt: string;
}

export interface Category {
  id: string;
  name: string;
  icon?: string;
}