import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-anti-hero-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './anti-hero-form.component.html',
  styleUrl: './anti-hero-form.component.scss'
})
export class AntiHeroFormComponent {
  
  antiHeroForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.antiHeroForm = this.fb.group({
      firstName: ['', Validators.required, Validators.minLength(1)],
      lastName: ['', Validators.required, Validators.minLength(1)],
      house: ['', Validators.required, Validators.minLength(1)],
      knownAs: ['', Validators.required, Validators.minLength(1)]
    })
  }

  clear() {
    this.antiHeroForm.reset();
  }
}
