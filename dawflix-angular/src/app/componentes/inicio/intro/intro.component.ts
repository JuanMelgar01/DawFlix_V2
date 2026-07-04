import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-intro',
  imports: [],
  templateUrl: './intro.component.html',
  styleUrl: './intro.component.scss',
})
export class IntroComponent implements OnInit, OnDestroy {
  private readonly router = inject(Router);
  private timeoutId?: number;

  ngOnInit(): void {
    this.timeoutId = window.setTimeout(() => {
      void this.router.navigateByUrl('/landing');
    }, 2400);
  }

  ngOnDestroy(): void {
    if (this.timeoutId !== undefined) {
      window.clearTimeout(this.timeoutId);
    }
  }
}