import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AntiHeroFormComponent } from './anti-hero-form.component';

describe('AntiHeroFormComponent', () => {
  let component: AntiHeroFormComponent;
  let fixture: ComponentFixture<AntiHeroFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AntiHeroFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AntiHeroFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
