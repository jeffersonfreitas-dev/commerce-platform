import { computed, Injectable, signal } from '@angular/core';
import { Category, Product } from '../models/product.model'

@Injectable({ providedIn: 'root'})
export class ProductService{
    private _products = signal<Product[]>(MOCK_PRODUCTS);

    readonly products = this._products.asReadonly();
    readonly categories = signal<Category[]>(MOCK_CATEGORIES).asReadonly();

    readonly promotions = computed(() =>
        this._products().filter(p => p.originalPrice && p.originalPrice > p.price)
    );    

    getById(id: string) {
        return this._products().find(p => p.id === id);
    }    

    search(query: string, category?: string): Product[] {
        const q = query.trim().toLowerCase();
        return this._products().filter(p => {
            const matchQ = !q ||
                p.name.toLowerCase().includes(q) ||
                p.description.toLowerCase().includes(q) ||
                p.tags?.some(t => t.toLowerCase().includes(q));
            const matchC = !category || p.category === category;
            return matchQ && matchC;
        });
    }    
}

/* -------- Mock -------- */
const MOCK_CATEGORIES: Category[] = [
  { id: 'eletronicos', name: 'Eletrônicos' },
  { id: 'moda',        name: 'Moda' },
  { id: 'casa',        name: 'Casa' },
  { id: 'esportes',    name: 'Esportes' },
];

const img = (id: number) => `https://picsum.photos/seed/ecom-${id}/600/600`;

const MOCK_PRODUCTS: Product[] = Array.from({ length: 24 }).map((_, i) => {
  const id = i + 1;
  const base = 49 + (i * 37) % 900;
  const promo = i % 3 === 0;
  return {
    id: String(id),
    name: `Produto ${id} - ${['Fone', 'Camiseta', 'Cafeteira', 'Tênis'][i % 4]} Premium`,
    description:
      'Produto de alta qualidade com design moderno e acabamento premium. ' +
      'Ideal para o dia a dia, combina durabilidade e estilo.',
    price: promo ? base * 0.8 : base,
    originalPrice: promo ? base : undefined,
    imageUrl: img(id),
    gallery: [img(id), img(id + 100), img(id + 200)],
    category: MOCK_CATEGORIES[i % MOCK_CATEGORIES.length].id,
    stock: 5 + (i % 20),
    rating: 3.5 + (i % 5) * 0.3,
    reviews: 10 + (i * 7) % 400,
    tags: ['destaque', 'promoção'],
    createdAt: new Date(Date.now() - i * 864e5).toISOString(),
  };
});

