import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  template: `
    <footer class="ecom-footer">
      <div class="container flex-between" style="flex-wrap:wrap;gap:12px">
        <span>© {{ year }} Ecom Store — Todos os direitos reservados.</span>
        <span>Pagamento seguro · Entrega rápida</span>
      </div>
    </footer>
  `,
  styles: [`
    .ecom-footer {
      padding: 24px 0; margin-top: 40px;
      border-top: 1px solid rgba(203,213,225,.6);
      color: var(--ecom-muted); font-size: 13px;
    }
  `]
})
export class FooterComponent { year = new Date().getFullYear(); }