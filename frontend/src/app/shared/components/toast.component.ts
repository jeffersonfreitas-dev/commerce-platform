import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastService } from '../../core/services/toast.service';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="toast-wrap">
      @for (t of toasts.items(); track t.id) {
        <div class="toast" [class]="'toast--' + t.type">{{ t.text }}</div>
      }
    </div>
  `,
  styles: [`
    .toast-wrap {
      position: fixed; right: 20px; bottom: 20px; z-index: 100;
      display: flex; flex-direction: column; gap: 10px;
    }
    .toast {
      padding: 12px 16px; border-radius: 10px; font-size: 14px;
      background: #fff; border: 1px solid var(--ecom-border);
      box-shadow: var(--ecom-shadow);
      animation: toastIn .25s ease-out;
    }
    .toast--success { border-color: #bbf7d0; background:#f0fdf4; color:#166534; }
    .toast--error   { border-color: #fecaca; background:#fef2f2; color:#991b1b; }
    @keyframes toastIn { from { opacity:0; transform: translateY(8px);} to {opacity:1;transform:translateY(0);} }
  `]
})
export class ToastComponent { 
    toasts = inject(ToastService); 
}